
require 'uri'
require 'net/http'
require 'openssl'
require 'rubygems'
require 'json'

# This file contains examples of how to interact with Lexigram APIs in Python
# Each of the examples inspect the responses printing some relevant properties.
# For comprehensive documentation that includes information of all API responses
# please visit http://docs.lexigram.io
api_key = "your_apikey"

def create_http(url)
  url = URI(url)
  http = Net::HTTP.new(url.host, url.port)
  http.use_ssl = true
  # http.verify_mode = OpenSSL::SSL::VERIFY_NONE
  return http, url
end

def get(url, api_key, params={})
  http, url = create_http(url)
  url.query = URI.encode_www_form(params)

  request = Net::HTTP::Get.new(url)
  request["authorization"] = "Bearer #{api_key}"

  response = http.request(request)
  JSON.parse(response.read_body)
end

def post(url, api_key, params={})
  http, url = create_http(url)

  request = Net::HTTP::Post.new(url)
  request["authorization"] = "Bearer #{api_key}"
  request["content-type"] = 'application/json'
  request.body = params.to_json