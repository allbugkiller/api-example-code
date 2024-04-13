
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

  response = http.request(request)
  JSON.parse(response.read_body)
end

# Entity extraction from a note sample.
def example_entity_extraction(api_key)
  text = "The patient was given some hydrocodone for control of her pain."+
         "The patient suffers from bulimia and eating disorder, bipolar disorder,"+
         "and severe hypokalemia. She thinks her potassium might again be low.";
  params = {text: text}
  url = "https://api.lexigram.io/v1/extract/entities"
  parsed_json = post(url, api_key, params)

  # For loop that inspects the response printing to console the extracted concepts.
  # It prints the Lexigraph concept ID, the type of concepts extracted (problem, drug, etc)
  # and the context (negation, speculation, ...)
  puts "", "", "~~~~~~~~~~~~ example_entity_extraction ~~~~~~~~~~~~"
  parsed_json['matches'].each do |hit|
    puts "#{hit['label']} (#{hit['id']} - #{hit['types'].join(", ")})"
  end
end