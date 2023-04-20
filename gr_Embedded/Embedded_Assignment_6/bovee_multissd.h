#ifndef bovee_m_ssd_h
#define bovee_m_ssd_h

class multi_SSD {
public:
  multi_SSD(int a, int b, int c, int d, int e, int f, int g, int h, int d0, int d1, int d2, int d3);
  void begin();
  void display(int *vals, bool *dps);
  void set_display(int val, bool dp);
private:
  int _a, _b, _c, _d, _e, _f, _g, _h;
  int _d0, _d1, _d2, _d3;
  int display_pin(int target);
};

#endif
