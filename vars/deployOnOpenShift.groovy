#!/usr/bin/env groovy
//def call(String openshiftCluster, String openshiftProject, String imageName) {
	
	// Update deployment.yaml with new Docker Hub image
//	sh "sed -i 's|image:.*|image: ${imageName}:${BUILD_NUMBER}|g' deployment.yaml"

	// Login and Deploy on OpenShif cluster
//	openshift.withCluster(clustername: "${openshiftCluster}" ,serverurlVariable: 'serverURL', tokenVariable: 'token') {
//		openshift.withProject("${openshiftProject}") {
//			sh "oc login --server=${serverURL} --token=${token} --insecure-skip-tls-verify"
//			sh "oc project "${openshiftProject}""
//			sh "oc apply -f ." 
//		}
//	}
//}

def call(String openshiftCluster, String openshiftProject, String imageName) {
    // Update deployment.yaml with new Docker Hub image
    sh "sed -i 's|image:.*|image: ${imageName}:${BUILD_NUMBER}|g' deployment.yaml"

    // Login and Deploy on OpenShift cluster
    openshift.withCluster(
        cluster: openshiftCluster,
        serverUrlVariable: 'serverURL', // Use serverUrlVariable instead of serverurlVariable
        tokenVariable: 'token'
    ) {
        openshift.withProject("${openshiftProject}") {
            // Use the variable serverURL here instead of ${serverURL}
            sh "oc login --server=${serverURL} --token=${token} --insecure-skip-tls-verify"
            sh "oc project ${openshiftProject}"
            sh "oc apply -f ."
        }
    }
}
