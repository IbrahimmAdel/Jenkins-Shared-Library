#!/usr/bin/env groovy
//def call(String openshiftCluster, String openshiftProject, String imageName) {
    // Update deployment.yaml with new Docker Hub image
   // sh "sed -i 's|image:.*|image: ${imageName}:${BUILD_NUMBER}|g' deployment.yaml"

  //  openshift.withCluster("${openshiftCluster}") {
 //       openshift.withProject("${openshiftProject}") {
 //               //sh "oc login --server="${serverURL}" --token="${token}" --insecure-skip-tls-verify"
 //               sh "oc project ${openshiftProject}"
//                sh "oc apply -f ."
            
    //    }
  //  }
//}

def call(String openshiftCluster, String openshiftProject, String imageName) {
    // Update deployment.yaml with new Docker Hub image
    sh "sed -i 's|image:.*|image: ${imageName}:${BUILD_NUMBER}|g' deployment.yaml"

    openshift.withCluster(clustername: "${openshiftCluster}", serverUrlVariable: 'serverURL', tokenVariable: 'token') {
        openshift.withProject("${openshiftProject}") {
            script {
                // Use the 'script' block to run multiple 'sh' steps
                sh "oc login --server=${openshift.getApiServer()} --token=${openshift.getToken()}"
                sh "oc project ${openshiftProject}"
                sh "oc apply -f ."
            }
        }
    }
}

