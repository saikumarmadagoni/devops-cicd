def checkout(){
 stage("checkout"){
  node("master"){
   
   dir(env.svc_name){
    git branch:env.svc_branch ,url: "git@github.com:saikumarmadagoni/"+env.svc_name+".git" ,  credentialsId: "madagonitoken"
   }
   dir(env.svc_name) {
    sh 'ls -ltrh'
    publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: '', reportFiles: 'test.html', reportName: 'HTML Report', reportTitles: '', useWrapperFileDirectly: true])

   }
  }
 }
}
/*
def sonarscan(){
 stage("sonarscan"){

  node("master"){

   withSonarQubeEnv('sonarqube') {

       dir(env.svc_name){
    git branch:env.svc_branch ,url: "git@github.com:saikumarmadagoni/"+env.svc_name+".git" ,  credentialsId: "madagonitoken"
   }
      dir(env.svc_name){ 
       sh 'whoami'
       withMaven(maven: 'mvn') {
       sh 'mvn --version'
      
     sh 'mvn clean verify sonar:sonar -Dsonar.projectKey=spring-svc -Dsonar.projectName='spring-svc' -Dsonar.host.url=http://localhost:9000  || true '
       }
       }
      }
  }


 }

}
*/

def build(){

  stage("build"){

  node("master"){

       echo "entered the build"
       currentBuild.result = "UNSTABLE"

       dir(env.svc_name){
    git branch:env.svc_branch ,url: "git@github.com:saikumarmadagoni/"+env.svc_name+".git" ,  credentialsId: "madagonitoken"
   }
   echo "completed the git pull"
      dir(env.svc_name){ 
       sh 'whoami'
       withMaven(maven: 'mvn') {
       sh 'mvn --version'
      
     sh 'mvn clean install'

        stash includes: '**/target/*.jar' , name: 'application.jar'
       }
       }
      }
  }


 }

def createimage() {

 stage("create image") {
  node("master")  {
   dir("devops"){
    git branch:"main" ,url: "git@github.com:saikumarmadagoni/"+"devops-cicd"+".git" ,  credentialsId: "madagonitoken"
   }
   dir("devops")
   {
     unstash 'application.jar'
     sh 'ls -ltrh'
     sh 'ls -ltrh target'
    sh 'docker build -t svc:latest .'
   }
 }
 }
}


def deploy(){
 stage("run image") {
  node("master")  {
      sh 'docker run -d svc:latest' 

  }

 }


}
def qadeploy(){


}

return this
