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
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                echo '========== Running unit tests =========='
                bat 'mvn test'
            }
        }

        stage('Code Coverage') {
            steps {
                echo '========== Generating code coverage report =========='
                bat 'mvn jacoco:report'
            }
        }

        stage('Package') {
            steps {
                echo '========== Packaging application =========='
                bat 'mvn package -DskipTests'
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
