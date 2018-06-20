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
		stage('Build Docker Image') {
			gitCommit = sh(returnStdout: true, script: 'git rev-parse HEAD').trim()
			container('docker') {
                sh 'docker --version'
                sh 'docker build . -t debapriyalaha/user-service:latest-snapshot'   
           }
		}
		stage('Publish Docker Image'){
			container('docker') {
		    	
		      		sh 'docker push debapriyalaha/user-service'  
              	
            }
		}
	}

}