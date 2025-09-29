# Create SNS Topic
resource "aws_sns_topic" "sns_topic" {
    name = var.sns_topic_name
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

##############################################
# Dead Letter Queue (DLQ) Behavior
#
# • When enable_dlq = true (default):
#   - A DLQ queue will be created
#   - The main queue will be configured with a redrive_policy
#   - Messages that fail to be consumed more than
#     max_receive_count times will be moved to the DLQ
#
# • When enable_dlq = false:
#   - No DLQ queue will be created
#   - The main queue will not have a redrive_policy
#
##############################################

# DLQ (Optional)
resource "aws_sqs_queue" "dlq" {
    count = var.enable_dlq ? 1 : 0
    name = var.dlq_queue_name
    message_retention_seconds = var.delay_seconds
}

# Create SQS Queue with optional DLQ redrive policy 
resource "aws_sqs_queue" "sqs_queue" {
    name = var.sqs_queue_name
    delay_seconds = var.sqs_queue_delay_seconds
    message_retention_seconds = var.sqs_retention_seconds 

    # attach DLQ if enabled 
    redrive_policy = var.enable_dlq ? jsonencode({
        deadLetterTargetArn = aws_sqs_queue.dlq[0].arn
        maxReceiveCount = var.max_receive_count
    }) : null
}