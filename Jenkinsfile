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
                dir('chu-chu'){
                    sh "chmod +x gradlew;"
                    sh "./gradlew clean;"
                    sh "./gradlew build -x test;"
                }
            }
        }   // build 시 test 없이 진행하고, test는 추후에 거르기 위해 이렇게 이용함.

        stage('Gradle Junit Test') {
            steps {
                dir('chu-chu'){
                    sh "chmod +x gradlew; ./gradlew check"
                }
            }
        }   // test를 이용해서 확인함.
        stage('JUnit Test Publish') {
            steps {
                dir('chu-chu'){
                    junit '**/build/test-results/test/*.xml'
                }
            }
        }   // Junit 테스트 결과를 젠킨스 프로젝트 첫 화면에서 볼 수 있게 결과물을 출력한다. 이렇게 해주면 알아서 결과물을 보여준다.
        stage('Clean'){
            steps{
                dir('backend'){
                    sh "chmod +x gradlew;"
                    sh "./gradlew clean;"
                }
            }
        }
    }
}
