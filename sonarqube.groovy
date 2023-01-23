pipeline {
    agent { label 'JAVA' }
    triggers {
        cron('* * * * *')
    }
    stages {
        stage ('vcs') {
            steps {
                git url: 'https://github.com/longflewtinku/spring-petclinic.git', 
                branch: 'main'
            }
        }
        stage("build & SonarQube analysis") {
            steps {
              withSonarQubeEnv('sonarqube') {
                sh 'mvn package sonar:sonar'
              }
            }
          }
        stage ('build packages') {
            steps {
                sh 'mvn package'
            }
        }
        
    }

}