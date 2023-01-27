

pipeline {
    agent { label 'VEDHA' }
    parameters {
        choice(name: 'BRANCH_TO_BUILD', choices: ['REL_1.0', 'main'], description: 'Branch to build')
        string(name: 'MAVEN_GOAL', defaultValue: 'package', description: 'maven goal')
    }       
    triggers { pollSCM('H */4 * * 1-5') }
    stages {
        stage ('vcs') {
            steps {
                mail subject: "job started for Jenkins JOB $env JOB_NAME",
                body: "job started for Jenkins JOB $env JOB_NAME",
                to: "laxmanadapa777@gmail.com"
                git url: 'https://github.com/longflewtinku/spring-petclinic.git', 
                branch: "${params.BRANCH_TO_BUILD}"
            }
        } 
        stage("build & SonarQube analysis") {
               steps {
                 withSonarQubeEnv('sonarqube') {
                   sh 'mvn clean package sonar:sonar'
              }
            }
          }
        stage("Quality Gate") {
            steps {
                timeout(time: 1, unit: 'HOURS') {
                waitForQualityGate abortPipeline: true
              }
            }
          }
        stage ('Artifactory configuration') {
            steps {
                rtMavenDeployer (
                    id: "MAVEN_DEPLOYER",
                    serverId: "JFROG_ID",
                    releaseRepo: 'testing-libs-release-local',
                    snapshotRepo: 'testing-libs-snapshot-local'
                )
            }
        }
        stage ('Exec Maven') {
            steps {
                rtMavenRun (
                    tool: 'MVN-3.6.3', // Tool name from Jenkins configuration
                    pom: 'pom.xml',
                    goals: 'install',
                    deployerId: "MAVEN_DEPLOYER"
                )
            }
        }
        stage ('Publish build info') {
            steps {
                rtPublishBuildInfo (
                    serverId: "JFROG_ID"
                )
            }
        }
        stage ('archive results') {
            steps {
            junit '**/surefire-reports/*.xml'
            }
        }
    }   
    post {
        always {
            echo 'job complted'
            mail subject: "job completed for Jenkins JOB $env.JOB_NAME",
                 body: "job completed for Jenkins JOB $env.JOB_NAME /n click hear JOB_URL",
                 to: "laxmanadapa777@gmail.com"
        }
        failure {
            always {
                echo 'job failure'
                mail subject: "job completed for Jenkins JOB $env.JOB_NAME",
                body: "job completed for Jenkins JOB $env.JOB_NAME with JOB BUILD_ID",
                to: "laxmanadapa777@gmail.com"
            }
        }
        success {
            always {
                echo 'job success'
                mail subject: "job completed for Jenkins JOB $env.JOB_NAME",
                body: "job completed for Jenkins JOB $env.JOB_NAME with JOB BUILD_ID",
                to: "laxmanadapa777@gmail.com"
            }
        }
    }
}
       
        