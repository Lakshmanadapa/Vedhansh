pipeline {
    agent any
    stages {
        stage('test') {
            steps {
                sh 'echo hello'
            }
        }
        stage('learning') {
           agent { label 'ANSIBLE' }
            steps {
                git url: 'https://github.com/spring-projects/spring-petclinic.git' , 
                    branch: 'main'
            }
        }
    }
}