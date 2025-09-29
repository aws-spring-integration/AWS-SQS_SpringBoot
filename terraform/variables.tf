variable "delay_seconds" {
  description = "Message retention time for DLQ"
  type        = number
  default     = 1209600  # 14 days, maximum value defined for SQS
}

variable "enable_dlq" {
    description = "Whether to create and attach a dead letter queue (DLQ)"
    type = bool 
    default = true 
}

variable "dlq_queue_name" {
    description = "Name of the DQL queue (if enabled)"
    type = string
    default = "sqs-sns-lab-dlq"
}

variable "max_receive_count" {
    description = "Max number of receives before moving message to DLQ"
    type = number
    default = 5
}

variable "aws_region" {
    description = "AWS region to deploy resources in"
    type = string
    default = "us-east-1"
}

variable "aws_profile" {
    description = "AWS CLI profile name"
    type = string
    default = "default"
}

variable "sns_topic_name" {
    description = "Name of SNS topic"
    type = string
    default = "sqs-sns-lab-topic"
}

variable "sqs_queue_name" {
    description = "Name of the SQS queue"
    type = string
    default = "sqs-sns-lab-queue"
}

variable "sqs_queue_delay_seconds" {
    description = "Delay seconds for SQS queue"
    type = number
    default = 0
}

variable "sqs_retention_seconds" {
    description = "Message retention period in seconds"
    type = number 
    default = 345600 # 4 days
}