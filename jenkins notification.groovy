pipeline {
    agent { label 'VEDHA' }
    stages {
        stage('vcs') {
            steps {
                git url: 'https://github.com/longflewtinku/spring-petclinic.git' ,
                    branch: 'main'
            }
        }
        stage('build') {
            steps {
                sh 'mvn package'
            }
        }
    }
    post {
    success {
        slackSend channel: 'notification',
                  color: 'good',
                  message: "The pipeline success"
        }
    }
}