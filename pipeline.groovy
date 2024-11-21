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
  println 'Hello from example1'

}

def build(){


}
def deploy(){



}
def qadeploy(){


}

return this
