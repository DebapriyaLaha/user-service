podTemplate(label: 'user-service-pod-jenkins', containers: [
     containerTemplate(name: 'docker', image: 'docker', command: 'cat', ttyEnabled: true),
     containerTemplate(name: 'mongo', image: 'mongo', command: 'cat', ttyEnabled: true),
     containerTemplate(name: 'maven', image: 'maven:3.3.9-jdk-8-alpine', ttyEnabled: true, command: 'cat')
  ],
  envVars: [
        envVar(key: 'BRANCH_NAME', value: env.BRANCH_NAME),
        envVar(key: 'BUILD_NUMBER', value: env.BUILD_NUMBER)
  ],
  volumes: [
    hostPathVolume(mountPath: '/var/run/docker.sock', hostPath: '/var/run/docker.sock'),
]) {
	node('user-service-pod-jenkins') {	
	  
	  def app
	  def userInput=""
	  
		stage('checkout') {
			 checkout scm
		}
		stage('Maven Build') {
			 container('maven') {
			    sh 'echo $BRANCH_NAME'
			    sh 'echo $BUILD_NUMBER'
			 	sh 'mvn clean install --quiet -DskipTests'
			 }
		}
		stage('Check Mongo container') {
			container('mongo') {
                sh 'mongo localhost:27017'   
           }
		}
		stage('Build Docker Image') {
			container('docker') {
                sh 'docker build . -t debapriyalaha/user-service:$BRANCH_NAME-$BUILD_NUMBER'   
           }
		}
		stage('Publish Docker Image'){
			container('docker') {
				withCredentials([usernamePassword( credentialsId: 'dockerHub', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
					sh 'docker login -u ${USERNAME} -p ${PASSWORD}'
					sh 'docker push debapriyalaha/user-service:$BRANCH_NAME-$BUILD_NUMBER'  
            	}
			}
		}
		stage('Choose environment') {
  			userInput = input(message: 'Choose an environment',    
                    parameters: [ [$class: 'ChoiceParameterDefinition', choices: "Dev\nQA\nProd", name: 'Env']] 
			sh 'echo $userInput' 
  )
}
	}
}