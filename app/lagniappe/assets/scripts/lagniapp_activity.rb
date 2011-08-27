require 'ruboto'
require 'json'

ruboto_import_widgets :Button, :LinearLayout, :TextView, :EditText

java_import "org.apache.http.client.methods.HttpPut"
java_import "org.apache.http.entity.StringEntity"
java_import "org.apache.http.impl.client.DefaultHttpClient"

java_import 'android.content.Context'
java_import 'android.location.LocationManager'
ruboto_import 'com.b50.lagniappe.LocationListener'

$activity.handle_create do |bundle|
  setTitle 'Tag a deal!'

  setup_content do
    linear_layout :orientation => LinearLayout::VERTICAL do
      @text_view = text_view :text => 'Found a deal? Let people know about it!', :id => 42
      @description = edit_text :hint => "What's the deal?"
      @tags = edit_text :hint => "Tags"
      button :text => 'Tag it!'
    end
  end
  
  @loc_listener = LocationListener.new

  handle_click do |view|
    if view.text == 'Tag it!'
      
      lm = get_system_service(Context::LOCATION_SERVICE)
      
      begin
        gps = lm.provider_enabled?(LocationManager::GPS_PROVIDER)  
        
        if gps
          gps_loc = lm.get_last_known_location(LocationManager::GPS_PROVIDER)
        
          unless gps_loc.nil?
            put_location(@description.text, gps_loc.latitude, gps_loc.longitude)
          end
          
        end
      rescue  => exception
        puts "exception!!"
        puts exception.backtrace        
      end
      
      @tags.text = ""
      @description.text = ""      
    end
  end
  
  def self.put_location(desc, lat, long)
    json_msg = { :deal_description => desc, :latitude => lat, :longitude => long}.to_json
    entity = StringEntity.new(json_msg)
    entity.content_encoding = "UTF-8"
    entity.content_type = "application/json"
    put = HttpPut.new("http://ankara.herokuapp.com")
    put.entity = entity
    DefaultHttpClient.new().execute(put)
  end
  
  
end
