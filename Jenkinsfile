pipeline{
    agent any

    stages{
        stage('Build Jar'){
            steps{
                sh "mvn clean package -DskipTests"
            }
        }
        stage('Build Image'){
            steps{
                sh "docker build -t=nikolaiefimov/selenium ."
            }
        }
        stage('Push Image'){
            steps{
                echo "docker push nikolaiefimov/selenium"
            }
        }
    }
}