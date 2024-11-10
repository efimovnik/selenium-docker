pipeline{
    agent any

    stages{
        stage('Checkout Code') {
                    steps {
                        checkout([
                            $class: 'GitSCM',
                            branches: [[name: '*/main']],
                            userRemoteConfigs: [[url: 'https://github.com/efimovnik/selenium-docker.git']]
                        ])
                    }
                }
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