# Create SNS Topic
resource "aws_sns_topic" "sns_topic" {
    name = var.sns_topic_name
}

# Create SQS Queue
resource "aws_sqs_queue" "sqs_queue" {
    name = var.sqs_queue_name
    delay_seconds = var.sqs_queue_delay_seconds
    message_retention_seconds = var.sqs_retention_seconds
}

# Subscribe SQS Queue to SNS Topic
resource "aws_sns_topic_subscription" "sns_sqs_subscription" {
    topic_arn = aws_sns_topic.sns_topic.arn
    protocol = "sqs"
    endpoint = aws_sqs_queue.sqs_queue.arn

    # Required for SQS to allow SNS to send messages
    raw_message_delivery = true
}

# Allow SNS to publish to SQS
resource "aws_sqs_queue_policy" "sqs_policy" {
    queue_url = aws_sqs_queue.sqs_queue.id

    policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Effect = "Allow"
        Principal = "*"
        Action = "sqs:SendMessage"
        Resource = aws_sqs_queue.sqs_queue.arn
        Condition = {
          ArnEquals = {
            "aws:SourceArn" = aws_sns_topic.sns_topic.arn
          }
        }
      }
    ]
  })
}