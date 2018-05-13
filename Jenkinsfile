podTemplate(label: 'mypod', containers: [
    containerTemplate(name: 'maven', image: 'maven:3-alpine', ttyEnabled: true, command: 'cat')
  ],
  volumes: [
    hostPathVolume(mountPath: '/var/run/docker.sock', hostPath: '/var/run/docker.sock'),
]) {
	node('mypod') {
	
		stage('checkout') {
			 container('maven') {
			 	sh """
			 		whoami
			 		hostname
			 		ls -lrth /var/run/docker.sock
			 	"""
			 	checkout scm
			 }
		}
	
	}

}