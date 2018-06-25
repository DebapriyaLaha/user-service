podTemplate(label: 'user-service-pod-jenkins', containers: [
     containerTemplate(name: 'docker', image: 'docker', command: 'cat', ttyEnabled: true),
      containerTemplate(name: 'mongo', image: 'mongo', 
     				ports: [portMapping(name: 'mongo', containerPort: 27017, hostPort: 27017)], 
     				envVars: [
				                envVar(key: 'MONGO_INITDB_ROOT_USERNAME', value: 'root'),
				                envVar(key: 'MONGO_INITDB_ROOT_PASSWORD', value: 'pass')
				              ],
				    command: 'mongod'
     				),
     containerTemplate(name: 'maven', image: 'maven:3.3.9-jdk-8-alpine', ttyEnabled: true, command: 'cat')
  ],
  envVars: [
        envVar(key: 'BRANCH_NAME', value: env.BRANCH_NAME),
        envVar(key: 'BUILD_NUMBER', value: env.BUILD_NUMBER),
        envVar(key: 'MONGO_HOST', value: 'mongo'),
        envVar(key: 'MONGO_PORT', value: '27017')
  ],
  volumes: [
    hostPathVolume(mountPath: '/var/run/docker.sock', hostPath: '/var/run/docker.sock'),
]) {
	node('user-service-pod-jenkins') {	
	  
	  	def app
	  	def userInput = true
		def didTimeout = false
	  
		stage('checkout') {
			 checkout scm
		}
		stage('Check Mongo container') {
			container('mongo') {
                sh 'ps -ax | grep mongo'   
                sh 'mongo --quiet --eval  \"printjson(db.adminCommand('listDatabases'))\"'
           }
		}
		stage('Maven Build') {
			 container('maven') {
			    sh 'echo $BRANCH_NAME'
			 	sh 'mvn clean install -X -Dspring.data.mongodb.host=$MONGO_HOST -Dspring.data.mongodb.port=$MONGO_PORT -Dspring.data.mongodb.username=root -Dspring.data.mongodb.password=pass'
			 }
		}
		
		stage('Build Docker Image') {
			container('docker') {
                sh 'docker build . -t debapriyalaha/user-service:$BRANCH_NAME'   
           }
		}
		stage('Publish Docker Image'){
			container('docker') {
				withCredentials([usernamePassword( credentialsId: 'docker-hub-credentials', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
					sh 'docker login -u ${USERNAME} -p ${PASSWORD}'
					sh 'docker push debapriyalaha/user-service:$BRANCH_NAME'  
            	}
			}
		}
		stage('Proceed to DEV?') {
  			try {
					timeout(time: 60, unit: 'SECONDS') { // change to a convenient timeout for you
						userInput = input(
						id: 'Proceed1', message: 'Should proceed to DEV?', parameters: [
						[$class: 'BooleanParameterDefinition', defaultValue: true, description: '', name: 'Please confirm you agree with this']
						])
					}
				} catch(err) { // timeout reached or input false
					def user = err.getCauses()[0].getUser()
					if('SYSTEM' == user.toString()) { // SYSTEM means timeout.
						didTimeout = true
					} else {
						userInput = false
						echo "Aborted by: [${user}]"
					}
				}

		}
		stage('Deploy to DEV'){
			if (didTimeout) {
				// do something on timeout
				echo "no input was received before timeout"
			} else if (userInput == true) {
				// do something
				echo "this was successful"
			} else {
				// do something else
				echo "this was not successful"
				currentBuild.result = 'FAILURE'
			} 
		}
	}
}