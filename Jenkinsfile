podTemplate(label: 'user-service-pod-jenkins', containers: [
    containerTemplate(name: 'maven', image: 'maven:3-alpine', ttyEnabled: true, command: 'cat')
  ],
  volumes: [
    hostPathVolume(mountPath: '/var/run/docker.sock', hostPath: '/var/run/docker.sock'),
]) {
	node('user-service-pod-jenkins') {	
		stage('checkout') {
			 container('maven') {
			 	checkout scm
			 }
		}
		stage('checkout') {
			 container('maven') {
			 	sh 'java -version'
			 	sh 'mvn -version'
			 	sh 'pwd'
			 	sh 'cd user-service'
			 	sh 'mvn clean install'
			 }
		}
	}

}