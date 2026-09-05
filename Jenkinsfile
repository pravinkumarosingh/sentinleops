pipeline {
    agent any
    tools {
        maven 'Maven3'
        jdk 'JDK21'
    }
    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-creds')
        IMAGE_NAME = 'pravinkumarosingh/my-java-app'
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
        stage('Docker Build') {
            steps {
                sh 'docker build -t $IMAGE_NAME:latest .'
            }
        }
        stage('Push to Docker Hub') {
            steps {
                sh '''
                    echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin
                    docker push $IMAGE_NAME:latest
                '''
            }
        }
        stage('Deploy to App Server') {
            steps {
                sh """
                    ssh pravinkumarsingh@192.168.1.3 '
                        docker pull $IMAGE_NAME:latest
                        docker stop my-java-app-container || true
                        docker rm my-java-app-container || true
                        docker run -d --name my-java-app-container -p 8080:8080 $IMAGE_NAME:latest
                    '
                """
            }
        }
        stage('Verify Deployment') {
            steps {
                sh """
                    ssh pravinkumarsingh@192.168.1.3 '
                        sleep 20
                        docker ps --filter name=my-java-app-container --filter status=running | grep my-java-app-container
                    '
                """
            }
        }
    }
}