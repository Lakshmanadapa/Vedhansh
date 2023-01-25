pipeline {
    agent { label 'VEDHA' }
    triggers { pollSCM('H */4 * * 1-5') }
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
        stage ('Artifactory configuration') {
            steps {
                rtMavenDeployer (
                    id: "MAVEN_DEPLOYER",
                    serverId: "dev_id",
                    releaseRepo: 'qt-libs-release-local',
                    snapshotRepo: 'qt-libs-snapshot-local'
                )
            }
        }
        stage ('Exec Maven') {
            steps {
                rtMavenRun (
                    tool: 'MVN-3.6.3', // Tool name from Jenkins configuration
                    pom: 'pom.xml',
                    goals: 'package',
                    deployerId: "MAVEN_DEPLOYER"
                )
            }
        }
        
    }
}
       
        
    

