{{/* vim: set filetype=mustache: */}}
{{/*
Expand the name of the chart.
*/}}
{{- define "esbase.name" -}}
{{- default .Chart.Name .Values.nameOverride | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{/*
Create a default fully qualified app name.
We truncate at 63 chars because some Kubernetes name fields are limited to this (by the DNS naming spec).
If release name contains chart name it will be used as a full name.
*/}}
{{- define "esbase.fullname" -}}
{{- if .Values.fullnameOverride -}}
{{- .Values.fullnameOverride | trunc 63 | trimSuffix "-" -}}
{{- else -}}
{{- $name := default .Chart.Name .Values.nameOverride -}}
{{- if contains $name .Release.Name -}}
{{- .Release.Name | trunc 63 | trimSuffix "-" -}}
{{- else -}}
{{- printf "%s-%s" .Release.Name $name | trunc 63 | trimSuffix "-" -}}
{{- end -}}
{{- end -}}
{{- end -}}

{{- define "solr.server" }}
{{- if .Values.solr.serverOverride -}}
{{- .Values.solr.serverOverride }}
{{- else if eq .Release.Namespace "default" -}}
{{- printf "http://solr-0/solr" }}
{{- else -}}
{{- printf "http://solr-0-%s/solr" .Release.Namespace }}
{{- end -}}
{{- end -}}

{{- define "zook.server" }}
{{- if .Values.zook.serverOverride -}}
{{- .Values.zook.serverOverride }}
{{- else if eq .Release.Namespace "default" -}}
{{- printf "solr-zookeeper-0:2181" }}
{{- else -}}
{{- printf "solr-zookeeper-0-%s:2181" .Release.Namespace }}
{{- end -}}
{{- end -}}

{{/*
Create chart name and version as used by the chart label.
*/}}
{{- define "esbase.chart" -}}
{{- printf "%s-%s" .Chart.Name .Chart.Version | replace "+" "_" | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{/*
Common labels
*/}}
{{- define "esbase.labels" -}}
helm.sh/chart: {{ include "esbase.chart" . }}
{{ include "esbase.selectorLabels" . }}
{{- if .Chart.AppVersion }}
app.kubernetes.io/version: {{ .Chart.AppVersion | quote }}
{{- end }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{- end -}}

{{/*
Selector labels
*/}}
{{- define "esbase.selectorLabels" -}}
app.kubernetes.io/name: {{ include "esbase.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end -}}

{{/*
Create the name of the service account to use
*/}}
{{- define "esbase.serviceAccountName" -}}
{{- if .Values.serviceAccount.create -}}
    {{ default (include "esbase.fullname" .) .Values.serviceAccount.name }}
{{- else -}}
    {{ default "default" .Values.serviceAccount.name }}
{{- end -}}
{{- end -}}
