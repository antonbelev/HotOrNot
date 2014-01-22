from django.http import HttpResponse
from django.template import RequestContext
from django.shortcuts import render_to_response
from rango.models import Category, Page

def category(request, category_name_url):
    
    context = RequestContext(request)
    category_name = category_name_url.replace('_', ' ')
    context_dict = {'category_name' : category_name}
    
    
    try:
        category = Category.objects.get(name=category_name_url)
        pages = Page.objects.filter(category=category)
        
        context_dict['pages'] = pages
        context_dict['category'] = category
    except Category.DoesNotExist:
        pass
    return render_to_response('rango/category.html', context_dict, context)
    

def index(request):
    # Obtain the context from the HTTP request.
    context = RequestContext(request)

    # Query for categories - add the list to our context dictionary.
    category_list = Category.objects.order_by('-likes')[:5]
    pages_list = Page.objects.order_by('-views')[:5]
    context_dict = {'categories': category_list, 'pages' : pages_list}

    # The following two lines are new.
    # We loop through each category returned, and create a URL attribute.
    # This attribute stores an encoded URL (e.g. spaces replaced with underscores).
    for category in category_list:
        category.url = category.name.replace(' ', '_')

    # Render the response and return to the client.
    return render_to_response('rango/index.html', context_dict, context)
    
def about(request):
    return HttpResponse("Rango Says: Here is the about page.Back link <a href='/rango'>Back</a>")
