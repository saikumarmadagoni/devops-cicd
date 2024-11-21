def checkout(){
 stage("checkout"){
  node("master"){
   dir(env.svc_name){
    git branch:env.svc_branch ,url: "git@github.com:saikumarmadagoni/"+env.svc_name+".git" ,  credentialsId: "madagonitoken"
   }
  }
 }
}

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

def build(){

  stage("build"){

  node("master"){

       echo "entered the build"

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

def deploy(){



}
def qadeploy(){


}

return this
