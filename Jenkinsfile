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
            }
        }
