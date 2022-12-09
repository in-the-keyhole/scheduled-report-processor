# scheduled-report-processor

## To create a k8s cluster
`k3d cluster create solera`

## To apply the cronjob to the cluster
`kubectl -n default apply -f k8s/cronjob.yaml`

## To use k9s
`k9s`

## If the solera k3d cluster is not the default kubeconfig
`k3d kubeconfig merge solera --kubeconfig-merge-default`