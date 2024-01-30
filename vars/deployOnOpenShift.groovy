#!/usr/bin/env groovy
def call(String openshiftCluster, String openshiftProject, String imageName) {
	openshift.withCluster("${openshiftCluster}") {
                openshiftLogin(project: "${openshiftProject}")
			// Update deployment.yaml with new Docker Hub image
			sh "sed -i 's|image:.*|image: ${imageName}:${BUILD_NUMBER}|g' deployment.yaml"
			
			// Deploy on OpenShif cluster
			sh "oc apply -f ." 
		}
	}
