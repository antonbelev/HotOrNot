# Create your views here.

from django.http import HttpResponse
from django.shortcuts import render_to_response
from django.template import RequestContext

def base(request):
    context = RequestContext(request)
    context_dict = {}
    
    #read venues from db here
    venues = ['o2 Academy', 'Firhill Stadium', 'T.G.I. Friday\'s', 'University of Glasgow', 'Starbucks']
    
    context_dict['venues'] = venues    
    
    return render_to_response('home/base.html', context_dict, context)
