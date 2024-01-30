#!/usr/bin/env groovy
def call(String openshiftCluster, String openshiftProject, String imageName) {
	
	// Update deployment.yaml with new Docker Hub image
	sh "sed -i 's|image:.*|image: ${imageName}:${BUILD_NUMBER}|g' deployment.yaml"

	// Login and Deploy on OpenShif cluster
	openshift.withCluster() {
		openshift.withProject("${openshiftProject}") {
			sh "oc project '${openshiftProject}'"
			sh "oc apply -f ." 
		}
	}
}
