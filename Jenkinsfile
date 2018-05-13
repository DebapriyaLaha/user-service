podTemplate(label: 'user-service-pod-jenkins', containers: [
    containerTemplate(name: 'maven', image: 'maven:3-alpine', ttyEnabled: true, command: 'cat')
  ],
  volumes: [
    hostPathVolume(mountPath: '/var/run/docker.sock', hostPath: '/var/run/docker.sock'),
]) {
	node('user-service-pod-jenkins') {	
		stage('checkout') {
			 container('maven') {
			 	sh 'ls -lrth'
			 	checkout scm
			 	sh 'ls -lrth'
			 }
		}
		stage('checkout') {
			 container('maven') {
			 	sh 'mvn clean install'
			 }
		}
	}

}