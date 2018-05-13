pipeline {
  agent {
    docker {
      image 'maven:3-alpine'
      args '-v /root/.m2:/root/.m'
    }

  }
  stages {
    stage('Test') {
      steps {
        sh 'echo "Test"'
      }
    }
  }
}