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
			 	sh 'mvn clean install -DskipTests -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=war'
			 }
		}
		stage('Build Image') {
			 container('maven') {
			    whoami
			    docker --version
			 	app = docker.build("debapriyalaha/user-service")
			 }
		}
	}

}