#!/bin/bash

# reference: 	https://gist.github.com/domenic/ec8b0fc8ab45f39403dd

# Environment Variables Examples:
# export GH_TOKEN=721fd28bc24dTh1sIsN0tREaLc2f137b8b5f
# travis encrypt GH_TOKEN=721fd28bc24dTh1sIsN0tREaLc2f137b8b5f --add
# travis encrypt COMMIT_AUTHOR_EMAIL=<your_email_here> --add
# export GH_REF=github.com/<your_repo_path>.git

set -e # exit with nonzero exit code if anything fails

# go to the distributions directory and create a *new* Git repo
cd build/distributions
git init

# inside this git repo we'll pretend to be a new user
git config user.name "travis-ci"
git config user.email "${COMMIT_AUTHOR_EMAIL}"

# The first and only commit to this new Git repo contains all the
# files present with the commit message.
git add .
git commit -m "Deploy Travis CI Build #${TRAVIS_BUILD_NUMBER} artifacts to GitHub"

# Force push from the current repo's master branch to the remote
# repo's build-artifacts branch. (All previous history on the gh-pages branch
# will be lost, since we are overwriting it.) We redirect any output to
# /dev/null to hide any sensitive credential data that might otherwise be exposed.
git push --force --quiet "https://${GH_TOKEN}@${GH_REF}" master:build-artifacts > /dev/null 2>&1
