
pipeline {
    agent any
    options {
        buildDiscarder(logRotator(numToKeepStr: '5', daysToKeepStr: '5'))
        timestamps()
    }
    environment {
        DOCKERHUB_CREDENTIALS = credentials('preshtego-dockerhub')
    }
    stages {
        stage('Building Image') {
            steps {
                sh 'mvn clean install'
                sh 'docker build -t preshtego/cicd:$BUILD_NUMBER .'
            }
        }
        stage('Login to Docker') {
            steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }
        stage('docker Push') {
            steps {
                sh 'docker push preshtego/cicd:$BUILD_NUMBER'
            }
        }
    }
    post {
        always {
            sh 'docker logout'
        }
    }
}
# java project
