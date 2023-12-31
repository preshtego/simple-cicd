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
                sh 'mvn -B -DskipTests clean install'
                sh 'docker build -t preshtego/cicd:$BUILD_NUMBER .'
            }
        }
	stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Login to Docker') {
            steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }
        stage('Scan') {
            steps {
                sh 'trivy preshtego/cicd:$BUILD_NUMBER'
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
