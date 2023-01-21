pipeline {
    agent { label'OPEN' }
    stages {
        stage('vcs') {
            steps {
                git url: 'https://github.com/openmrs/openmrs-core.git',
                branch: 'master'
            }
        }
        stage('openmrs build') {
            steps {
                sh 'mvn clean package'
            }
        }

    }
}