pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo 'Building the project...'
                // Simulate build
                sh 'sleep 2'
            }
        }
        stage('Test') {
            steps {
                echo 'Running tests...'
                // Simulate testing
                sh 'sleep 2'
            }
        }
        stage('Deploy Approval') {
            steps {
                script {
                    // Prompt user for input during the pipeline
                    def userInput = input message: 'Approve deployment?',
                        parameters: [
                            string(name: 'DEPLOY_ENV', defaultValue: 'dev', description: 'Enter the environment (e.g., dev, staging, prod)')
                        ]
                    // Use the input parameter
                    echo "Deployment approved for environment: ${userInput}"
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    echo "Deploying to ${DEPLOY_ENV} environment..."
                    // Simulate deployment
                    sh 'sleep 2'
                }
            }
        }
    }
}
