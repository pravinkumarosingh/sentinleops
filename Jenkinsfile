pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'JDK21'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn -B clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn -B test'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t my-java-app:local .'
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                    docker stop my-java-app-container || true
                    docker rm my-java-app-container || true
                    docker run -d --name my-java-app-container -p 8080:8080 my-java-app:local
                '''
            }
        }
    }
}