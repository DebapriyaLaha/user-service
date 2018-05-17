podTemplate(label: 'user-service-pod-jenkins', containers: [
     containerTemplate(name: 'docker', image: 'docker', command: 'cat', ttyEnabled: true),
     containerTemplate(name: 'maven', image: 'maven:3.3.9-jdk-8-alpine', ttyEnabled: true, command: 'cat')
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
			 	sh 'mvn clean install --quiet -DskipTests'
			 }
		}
		stage('Build Docker Image') {
			gitCommit = sh(returnStdout: true, script: 'git rev-parse HEAD').trim()
			container('docker') {
                sh 'docker --version'
                sh 'docker build . -t user-service:latest-snapshot'   
           }
		}
		stage('Publish Docker Image'){
			container('docker') {
		    	withDockerRegistry([ credentialsId: "docker-hub-credentials", url: "" ]) {
		      		script{
		      			docker.image("user-service").push()
		      		}
              	}
            }
		}
	}

}