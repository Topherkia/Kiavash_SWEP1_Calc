pipeline {
    agent any

    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
        timeout(time: 1, unit: 'HOURS')
        timestamps()
    }

    stages {
        stage('Checkout') {
            steps {
                echo '========== Checking out source code =========='
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo '========== Building project with Maven =========='
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                echo '========== Running unit tests =========='
                sh 'mvn test'
            }
        }

        stage('Code Coverage') {
            steps {
                echo '========== Generating code coverage report =========='
                sh 'mvn jacoco:report'
            }
        }

        stage('Package') {
            steps {
                echo '========== Packaging application =========='
                sh 'mvn package -DskipTests'
            }
        }
    }

    post {
        always {
            echo '========== Pipeline finished =========='
        }

        success {
            echo '========== Build successful =========='
            // Archives the test results
            junit 'target/surefire-reports/**/*.xml'
            
            // Archives the code coverage report
            publishHTML([
                reportDir: 'target/site/jacoco',
                reportFiles: 'index.html',
                reportName: 'JaCoCo Code Coverage Report'
            ])
        }

        failure {
            echo '========== Build failed =========='
        }

        unstable {
            echo '========== Build unstable =========='
        }

        cleanup {
            echo '========== Cleaning up workspace =========='
            deleteDir()
        }
    }
}
