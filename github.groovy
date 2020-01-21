def call(String repository, String revision, String tag,
  String crendential, String username='', String email='') {
  def func = "[tagGithubRepository]"
  def tempFolder = '.tag-github-repository-temp'

  // configure git global var
  if (username) {
    sh "git config --global user.name \"${username}\""
  }
  if (email) {
    sh "git config --global user.email \"${email}\""
  }

  withCredentials([usernamePassword(
    credentialsId: crendential,
    passwordVariable: 'GIT_PASSWORD',
    usernameVariable: 'GIT_USERNAME'
  )]) {
    // tag repository
    sh """
    mkdir ${tempFolder}
    cd ${tempFolder}
    git init
    git remote add origin https://github.com/${repository}.git
    git fetch origin
    git checkout ${revision}
    git tag ${tag}
    git push --tags 'https://${GIT_USERNAME}:${GIT_PASSWORD}@github.com/${repository}.git'
    """
  }

  echo "${func} all done"
}
