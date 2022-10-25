pipeline {
    agent any
    stages{
        stage('Ready'){
            steps{
                sh "echo 'Ready'"
            }
        }

        stage('Spring Boot Clean & Build'){
            steps{
                sh "chmod +x gradlew;"
                sh "./gradlew clean;"
                sh "./gradlew build -x test;"
            }
        }   // build 시 test 없이 진행하고, test는 추후에 거르기 위해 이렇게 이용함.

        stage('Gradle Junit Test') {
            steps {
                sh "chmod +x gradlew; ./gradlew check"
            }
        }   // test를 이용해서 확인함.
        stage('JUnit Test Publish') {
            steps {
                junit '**/build/test-results/test/*.xml'
            }
        }   // Junit 테스트 결과를 젠킨스 프로젝트 첫 화면에서 볼 수 있게 결과물을 출력한다. 이렇게 해주면 알아서 결과물을 보여준다.
        stage('Clean'){
            steps{
                sh "chmod +x gradlew;"
                sh "./gradlew clean;"
            }
        }
        stage('deploy') {
            steps([$class: 'BapSshPromotionPublisherPlugin']){
                sshPublisher(
                    continueOnError: false, failOnError: true,
                    publishers: [
                        sshPublisherDesc(
                            configName: "chuchu-server",//Jenkins 시스템 정보에 사전 입력한 서버 ID
                            verbose: true,
                            transfers: [
                                sshTransfer(
                                    sourceFiles: "build/libs/chu-chu-0.0.1-SNAPSHOT.jar", //전송할 파일
                                    removePrefix: "build/libs", //파일에서 삭제할 경로가 있다면 작성
                                    remoteDirectory: "/root/chuchu/jar" //배포할 위치
                                    execCommand: "sudo /root/chuchu/deploy.sh" //원격지에서 실행할 커맨드
                                )
                            ]
                
                        )
                   )
            }
        }
    }
}
