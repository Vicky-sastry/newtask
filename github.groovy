def call(String repository, String revision, String tag,
  String crendential, String username='', String email='') {
  def func = "[tagGithubRepository]"
  def tempFolder = '.tag-github-repository-temp'

  // configure git global var
  if (username) {
    sh "git config --global user.name \"${bhavyabommisetti}\""
  }
  if (email) {
    sh "git config --global user.email \"${bhavya.bommisetti@gmail.com}\""
  }

  withCredentials([usernamePassword(
    credentialsId: git,
    passwordVariable: 'git123',
    usernameVariable: 'gitlab'
  )]) {
    // tag repository
    sh """
    mkdir ${temp}
    cd ${temp}
    git init
    git remote add origin https://github.com/${repository}.git
    git fetch origin
    git checkout ${revision}
    git tag ${tag}
    git push --tags 'https://${gitlab}:${git123}@github.com/${repository}.git'
    """
  }

  echo "${func} all done"
}
