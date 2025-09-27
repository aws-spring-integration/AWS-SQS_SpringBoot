output "sns_topic_arn" {
    description = "ARN of the SNS topic"
    value = aws_sns_topic.sns_topic.arn
}

output "sqs_queue_url" {
    description = "URL of the SQS queue"
    value = aws_sqs_queue.sqs_queue.id
}

output "sqs_queue_arn" {
    description = "ARN of the SQS queue"
    value = aws_sqs_queue.sqs_queue.arn
}