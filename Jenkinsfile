pipeline {
    agent any

    environment {
        DOCKER_CREDENTIALS_ID = 'yujeongoyj'
        DOCKER_IMAGE_PREFIX = 'yujeongoyj/nyamnyam-config-server'
        services = "server/config-server,server/eureka-server,server/gateway-server,service/admin-service,service/chat-service,service/post-service,service/restaurant-service,service/user-service"
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
                    dir('server/config-server') {
                        git branch: 'main', url: 'https://github.com/yujeongoyj/nyamnyam-config-server.git', credentialsId: 'jenkins_token'
                    }

                    dir ('server/config-server/src/main/resources/secret-server') {
                        git branch: 'main', url: 'https://github.com/yujeongoyj/nyamnyam-secret-server.git', credentialsId: 'jenkins_token'
                    }
                }
            }
        }

        stage('Build JAR') {
            steps {
                script {
                    sh 'chmod +x gradlew'

                    // services 환경 변수를 Groovy 리스트로 변환
                    def servicesList = env.services.split(',')

                    // 각 서비스에 대해 Gradle 빌드 수행 (테스트 제외)
                    servicesList.each { service ->
                        dir(service) {
                            sh "../../gradlew clean build --warning-mode all -x test"
                        }
                    }
                }
            }
        }

        stage('Compose Up') {  // docker-compose up을 실행하는 단계
            steps {
                script {
                    dir('nyamnyam.kr') {
                        // Docker Compose up 실행
                        try {
                            sh 'docker-compose up -d'
                        } catch (Exception e) {
                            echo "Docker Compose Up 실패: ${e.message}"

                            // Docker Compose logs 출력
                            sh 'docker-compose logs'

                            // 모든 컨테이너 종료
                            sh 'docker-compose down'

                            // 빌드를 실패로 표시
                            error("Docker Compose 단계에서 오류가 발생했습니다.")
                        }
                    }
                }
            }
        }

        // 필요한 경우 추가 단계
        stage('Cleanup') {
            steps {
                script {
                    // Docker Compose를 사용한 후 정리 작업
                    sh 'docker-compose down'
                }
            }
        }
    }
}
