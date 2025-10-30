pipeline {
    agent any

    environment {
        // 定义环境变量
        REGISTRY = "192.168.101.131:8083"
        PROJECT = "library"
        APP_NAME = "demo-app"
        // Jenkins中需要配置名为'harbor-creds'的凭证，类型为Username and Password
        HARBOR_CREDENTIALS = credentials('harbor-creds')
    }

    stages {
        stage('Checkout') {
            steps {
                // Jenkins中需要配置名为'gitea-ssh'的SSH凭证
                git branch: 'main',
                credentialsId: 'gitea-ssh',
                url: 'ssh://gitea@192.168.101.131:2222/gitea/demo-app.git'
            }
        }

        stage('Build with Gradle') {
            steps {
                sh './gradlew build -x test'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    dockerImage = docker.build("$REGISTRY/$PROJECT/$APP_NAME:${env.BUILD_ID}")
                }
            }
        }

        stage('Push Image to Harbor') {
            steps {
                script {
                    docker.withRegistry("http://$REGISTRY", 'harbor-creds') {
                        dockerImage.push()
                    }
                }
            }
        }
    }

    post {
        always {
            echo 'Cleanup build environment'
            sh './gradlew clean'
        }
        success {
            echo "Build Success! Image: $REGISTRY/$PROJECT/$APP_NAME:${env.BUILD_ID}"
        }
        failure {
            echo 'Build Failed!'
        }
    }
}