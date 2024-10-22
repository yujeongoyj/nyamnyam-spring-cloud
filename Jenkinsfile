 stage('Grant execute permissions') {
                steps {
                    // gradlew 파일에 실행 권한 부여
                    sh 'chmod +x gradlew'
                }
            }

            stages {
                    stage('Build Config Server') {
                        steps {
                                sh 'cd nyamnyam-spring-cloud/server/config-server && ./gradlew build'
                        }
                    }
                    stage('Build Eureka Server') {
                        steps {
                            sh 'cd nyamnyam-spring-cloud/server/eureka-server && ./gradlew build'
                        }
                    }
                    stage('Build Gateway') {
                        steps {
                            sh 'cd nyamnyam-spring-cloud/server/gateway-server && ./gradlew build'
                        }
                    }
                    stage('Build Other Microservices') {
                        steps {
                                sh './gradlew -p nyamnyam-spring-cloud/service/admin-service build'
                                sh './gradlew -p nyamnyam-spring-cloud/service/chat-service build'
                                sh './gradlew -p nyamnyam-spring-cloud/service/post-service build'
                                sh './gradlew -p nyamnyam-spring-cloud/service/restaurant-service build'
                                sh './gradlew -p nyamnyam-spring-cloud/service/user-service build'
                        }
                    }
            }