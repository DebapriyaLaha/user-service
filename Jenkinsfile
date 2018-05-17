podTemplate(label: 'user-service-pod-jenkins', containers: [
    containerTemplate(name: 'maven', image: 'maven:3-alpine', ttyEnabled: true, command: 'cat')
  ],
  volumes: [
    hostPathVolume(mountPath: '/var/run/docker.sock', hostPath: '/var/run/docker.sock'),
]) {
	node('user-service-pod-jenkins') {	
	  
	  def app
	  
		stage('checkout') {
			 container('maven') {
			 	checkout scm
			 }
		}
		stage('Maven Build') {
			 container('maven') {
			 	sh 'mvn clean install -DskipTests'
			 }
		}
		stage('Build Image') {
			 container('maven') {
			 	app = docker.build("debapriyalaha/user-service")
			 }
		}
	}

}