pipeline {
	agent any
	stages {
		stage('Welcome'){
			steps {
				echo "Hello World!!! Welcome to my Java Build"
				powershell(". '.\\dir_Build.ps1'")
			      }
		}
		stage('build'){
			steps {
				git (
					branch: 'myJavaLocal',
					credentialsId: 'KiranPadmakar',
					url: 'https://github.com/kiranpadmakar/Java.git'
				)
				withAnt(installation: 'ANT', jdk: 'Java'){
				bat 'ant main'
				}
			}
		}
	
		stage('Move'){
			steps {
				echo "Move the jar"
				powershell(". '.\\mv_Build.ps1'")
			      }
		}
		stage('UploadtoS3'){
			steps {
				echo "Upload to S3 in AWS"

				withAWS(credentials: 'AWSId',region:'ap-southeast-2')
				{
				 Date datetime = new Date('yyyy-MM-dd-HH-mm-ss')
				s3Upload(
						acl: 'PublicRead', 
						bucket: 'my-java-builds', 
						cacheControl: '', 
						file: '', 
						metadatas: [''], 
						pathStyleAccessEnabled: true, 
						sseAlgorithm: 'AES256', 
						workingDir: 'dist/'
					)
				}
			      }
		}

	}

}
