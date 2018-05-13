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
			 	sh 'ls -lrth'
			 }
		}
		stage('checkout') {
			 container('maven') {
			 	sh 'cd user-service'
			 	sh 'mvn clean install'
			 }
		}
	}

}