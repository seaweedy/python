 
from django.http import HttpResponse
from django.shortcuts import render

def index(request):
  return HttpResponse("index page")

def index2(request):
  return render(request,'headache.html')
