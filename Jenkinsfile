pipeline {
    agent any

    environment {
        DOCKER_CREDENTIALS_ID = 'yujeongoyj'
        DOCKER_IMAGE_PREFIX = 'yujeongoyj/nyamnyam-config-server'
    }

    stages {
        stage('Checkout SCM') {
            steps {
                script {
                    dir('nyamnyam.kr') {
                        checkout scm
                    }
                }
            }
        }

        stage('Git Clone') {
            steps {
                script {
                    dir('nyamnyam.kr/server/config-server') {
                        git branch: 'main', url: 'https://github.com/yujeongoyj/nyamnyam-config-server.git', credentialsId: 'jenkins_token'
                    }

                    dir ('nyamnyam.kr/server/config-server/src/main/resources/secret-server') {
                        git branch: 'main', url: 'https://github.com/yujeongoyj/nyamnyam-secret-server.git', credentialsId: 'jenkins_token'
                    }
                }
            }
        }

        stage('Build JAR') {
            steps {
                script {
                    dir('nyamnyam.kr') {
                        sh 'chmod +x gradlew' // gradlew에 실행 권한 부여

                        // 각 서버에 대해 gradlew를 실행하고, --warning-mode all 옵션 추가
                        def services = [
                            'server/config-server',
                            'server/eureka-server',
                            'server/gateway-server',
                            'service/admin-service',
                            'service/chat-service',
                            'service/post-service',
                            'service/restaurant-service',
                            'service/user-service'
                        ]

                        for (service in services) {
                            dir(service) {
                                sh "../../gradlew clean build --warning-mode all"
                                // 테스트 실행 및 실패 시 처리
                                def testResult = sh(script: "../../gradlew test", returnStatus: true)
                                if (testResult != 0) {
                                    error "Tests failed for ${service}"
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
